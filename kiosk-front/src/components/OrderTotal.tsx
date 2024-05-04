interface OrderList {
  productName: string;
  price: number;
}

interface OrderTotalProps {
  orderLists: OrderList[];
  totalQuantity: number;
  totalPrice: number;
}

function OrderTotal({
  orderLists,
  totalQuantity,
  totalPrice,
}: OrderTotalProps) {
  if (!orderLists || orderLists.length === 0) {
    return <div>주문 목록이 없습니다.</div>;
  }

  return (
    <div className='flex mt-5'>
      <h3 className='text-lg font-bold'>총 수량 {totalQuantity}개</h3>
      <h3 className='text-lg text-red-600 ml-10 font-bold'>
        총 가격 {totalPrice}원
      </h3>
    </div>
  );
}

export default OrderTotal;
