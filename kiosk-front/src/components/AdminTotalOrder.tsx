import { useEffect, useState } from 'react';

function AdminTotalOrder() {
  const [orders, setOrders] = useState<
    {
      id: number;
      productName: string;
      totalPrice: number;
      option: string;
      state: string;
      date: string;
    }[]
  >([]);

  useEffect(() => {
    const exampleData = [
      {
        id: 1,
        productName: '치즈버거',
        totalPrice: 100000,
        option: '단품',
        state: '판매중',
        date: '2024-05-01',
      },
      {
        id: 2,
        productName: '치킨버거',
        totalPrice: 150000,
        option: '세트',
        state: '품절',
        date: '2024-05-02',
      },
      {
        id: 3,
        productName: '감자튀김',
        totalPrice: 50000,
        option: '개별',
        state: '판매중',
        date: '2024-05-03',
      },
    ];
    setOrders(exampleData);
  }, []);

  const [startDate, setStartDate] = useState<string | null>(null);
  const [endDate, setEndDate] = useState<string | null>(null);

  const filterOrdersByDateRange = () => {
    if (!startDate || !endDate) return orders;
    return orders.filter(
      (order) => order.date >= startDate && order.date <= endDate
    );
  };

  const calculateTotalPriceInRange = () => {
    const filteredOrders = filterOrdersByDateRange();
    return filteredOrders.reduce((sum, order) => sum + order.totalPrice, 0);
  };

  return (
    <div>
      <div className='flex items-center justify-end mr-5 mb-3'>
        <input
          type='date'
          id='startDate'
          value={startDate || ''}
          onChange={(e) => setStartDate(e.target.value)}
        />
        <input
          type='date'
          id='endDate'
          value={endDate || ''}
          onChange={(e) => setEndDate(e.target.value)}
        />
      </div>
      <div>
        <table className='border-2 p-2 mx-5 mb-5'>
          <thead className='bg-green-500'>
            <tr>
              <th className='border border-gray-400 p-2 text-white'>번호</th>
              <th className='border border-gray-400 p-2 text-white'>제품명</th>
              <th className='border border-gray-400 p-2 text-white'>
                주문금액
              </th>
              <th className='border border-gray-400 p-2 text-white'>옵션</th>
              <th className='border border-gray-400 p-2 text-white'>
                주문상태
              </th>
              <th className='border border-gray-400 p-2 text-white'>
                주문날짜
              </th>
            </tr>
          </thead>
          <tbody className='text-gray-700'>
            {filterOrdersByDateRange().map((order) => (
              <tr key={order.id} className='hover:bg-slate-200 hover:font-bold'>
                <td className='border border-gray-400 px-4 py-1'>{order.id}</td>
                <td className='border border-gray-400 px-4'>
                  {order.productName}
                </td>
                <td className='border border-gray-400 px-4'>
                  {order.totalPrice}
                </td>
                <td className='border border-gray-400 px-4'>{order.option}</td>
                <td className='border border-gray-400 px-4'>{order.state}</td>
                <td className='border border-gray-400 px-4'>{order.date}</td>
              </tr>
            ))}
          </tbody>
        </table>
        <p className='flex justify-end mr-5 items-center'>
          선택 기간 주문 총액 :
          <span className='text-red-500 font-bold items-center justify-center'>
            {calculateTotalPriceInRange()}
          </span>
          원
        </p>
      </div>
    </div>
  );
}

export default AdminTotalOrder;
