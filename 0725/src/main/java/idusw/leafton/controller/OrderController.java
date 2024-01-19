package idusw.leafton.controller;

import idusw.leafton.model.DTO.*;
import idusw.leafton.model.entity.CartItem;
import idusw.leafton.model.entity.OrderItem;
import idusw.leafton.model.service.CartService;
import idusw.leafton.model.service.MemberService;
import idusw.leafton.model.service.OrderService;
import idusw.leafton.model.service.ProductService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping(value = "/pay")
@RequiredArgsConstructor
@Controller
public class OrderController {

    private final MemberService memberService;
    private final CartService cartService;
    private final OrderService orderService;

    //주문 페이지로 이동(userCartPage와 기능은 동일 -> 이 메소드를 안 사용하는 방법 고민해봐야 할 듯)
    @GetMapping(value = "/buy/{memberId}")
    public String goBuy(@PathVariable("memberId") Long memberId, @RequestParam("checkedItems") String checkedItems, Model model, HttpSession session){
        MemberDTO member = memberService.getMemberById(memberId);
        if(member != null) {

            String[] itemIds = checkedItems.split(",");
            session.setAttribute("itemIds", itemIds); // 세션에 itemIds 저장 -> 다음 요청에서도 사용해야해서

            // 각 상품의 가격을 합산하여 총 가격 계산
            int totalPrice = 0; // 최종 주문 정보에 나타내기 위한 물품 총 가격


            for(String itemId : itemIds) {
                CartItemDTO cartItemById = cartService.findCartItemById(Long.parseLong(itemId));
                totalPrice += cartItemById.getCount() * cartItemById.getProductDTO().getPrice() * (1 - cartItemById.getProductDTO().getSalePercentage()/100.0);
            }

            model.addAttribute("totalPrice", totalPrice);

            return "pay/buy";
        }else {
            return "redirect:/main/index";
        }
    }

    @PostMapping(value = "/order/{memberId}")
    public String orderCheckout(@PathVariable("memberId") Long memberId, @ModelAttribute OrderDTO orderDTO, HttpSession session, Model model){
        MemberDTO member = memberService.getMemberById(memberId);
        if(member != null) {
            //goBuy()메서드에서 저장한 itemIds 세션 정보 가져오기
            String[] itemIds = (String[]) session.getAttribute("itemIds");

            //itemIds 세션 정보를 이용해 체크한 상품의 총 가격을 계산 -> order Entity에 저장해야함
            // 총 주문 상품 금액 계산 및 주문한 상품
            int totalPrice = 0;

            for(String itemId : itemIds) {
                CartItemDTO cartItemById = cartService.findCartItemById(Long.parseLong(itemId));
                if(cartItemById.getProductDTO().getAmount() == 0 || cartItemById.getProductDTO().getAmount() < cartItemById.getCount()){
                    return "redirect:/main/index"; // 주문한 상품의 재고가 없거나 재고보다 주문한 상품의 수량이 더 많으면 메인페이지로 이동
                }
                totalPrice += cartItemById.getCount() * cartItemById.getProductDTO().getPrice() * (1 - cartItemById.getProductDTO().getSalePercentage()/100.0);

            }

            //배송 회사 지정
            String deliveryCompany = "test1";
            // 배송비 계산
            int deliveryFee = 3000; // 기본 배송비
            if(totalPrice >= 100000) {
                // 주문 금액이 10만원 이상이면 배송비 무료
                deliveryFee = 0;
            }

            //주문 정보를 orderDTO에 저장
            orderDTO.setMemberDTO(member);
            //위에서 계산한 최종 주문 가격을 orderDTO에 저장
            orderDTO.setOrderPrice(totalPrice);
            orderDTO.setDeliveryFee(deliveryFee);
            orderDTO.setDeliveryCompany(deliveryCompany);

            // Order 객체를 DB에 저장하고, 저장된 Order 객체를 반환받음 -> 이렇게 따로 반환받지 않으면 orderItem을 생성할 때 문제 발생
            OrderDTO savedOrderDTO = orderService.addOrder(orderDTO);

            List<OrderItemDTO> orderItemList = new ArrayList<>(); //주문한 상품을 담을 리스트 생성

            for(String itemId : itemIds){
                //장바구니에서 선택한 CartItemId를 찾아오기
                CartItemDTO cartItemById = cartService.findCartItemById(Long.parseLong(itemId));
                //찾은 정보로 OrderItem을 생성
                OrderItemDTO orderItem = orderService.addOrderItem(savedOrderDTO, cartItemById.getProductDTO(), cartItemById.getCount());
                //생성한 OrderItem을 리스트에 담기
                orderItemList.add(orderItem);
                //OrderItem에 담은 CartItem은 더 이상 필요가 없으므로 삭제 처리
                cartService.cartItemDelete(cartItemById.getCartItemId());
            }

            model.addAttribute("totalPrice", totalPrice);
            model.addAttribute("order", savedOrderDTO);
            model.addAttribute("orderItems", orderItemList);

            return "/pay/complete";
        }else{
            return "redirect:/main/index";
        }
    }

}