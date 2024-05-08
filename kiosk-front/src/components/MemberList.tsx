import { useEffect, useState } from 'react';

function MemberList() {
  const [members, setMembers] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetch('API_URL_HERE'); // api url
        const data = await response.json();
        setMembers(data);
      } catch (error) {
        console.error('Error fetching member data:', error);
      }
    };
    fetchData();
  }, []);

  const totalMember = members.length;

  return (
    <div>
      <p>
        총 <span className='text-red-500 font-bold'>{totalMember}</span>
        명의 회원이 있습니다.
      </p>
      <table className='border-2 p-2 ml-5'>
        <thead className='w-10 h-6'>
          <td>순번</td>
          <td>휴대폰 번호</td>
          <td>암호</td>
          <td>포인트</td>
          <td>가입일</td>
          <td>삭제</td>
        </thead>
        <tbody>{}</tbody>
      </table>
    </div>
  );
}

export default MemberList;
