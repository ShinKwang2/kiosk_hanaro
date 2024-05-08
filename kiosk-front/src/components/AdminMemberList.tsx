import { useEffect, useState } from 'react';

function MemberList() {
  const [members, setMembers] = useState<
    {
      id: number;
      phoneNumber: string;
      point: number;
      date: string;
    }[]
  >([]);

  useEffect(() => {
    const exampleData = [
      {
        id: 1,
        phoneNumber: '01088887777',
        point: 300,
        date: '2024-05-01',
      },
      {
        id: 2,
        phoneNumber: '01088887777',
        point: 250,
        date: '2024-05-02',
      },
      {
        id: 3,
        phoneNumber: '01088887777',
        point: 250,
        date: '2024-05-02',
      },
    ];
    setMembers(exampleData);
  }, []);

  function handleDelete(id: number) {
    const updatedMembers = members.filter((member) => member.id !== id);
    setMembers(updatedMembers);
  }

  const totalMember = members.length;

  return (
    <div className='w-max'>
      <p className='font-bold mb-5'>
        총 <span className='text-red-500 font-bold'>{totalMember}</span>
        명의 회원이 있습니다.
      </p>
      <table className='p-2 mx-5 rounded-lg'>
        <thead className='bg-green-500'>
          <th className='border border-gray-400 p-2 text-white'>순번</th>
          <th className='border border-gray-400 p-2 text-white'>휴대폰 번호</th>
          <th className='border border-gray-400 p-2 text-white'>포인트</th>
          <th className='border border-gray-400 p-2 text-white'>가입일</th>
          <th className='border border-gray-400 p-2 text-white'>삭제</th>
        </thead>
        <tbody className='text-gray-700'>
          {members.map((member) => (
            <tr key={member.id} className='hover:bg-slate-200 hover:font-bold'>
              <td className='border border-gray-400 px-4'>{member.id}</td>
              <td className='border border-gray-400 px-4'>
                {member.phoneNumber}
              </td>
              <td className='border border-gray-400 px-4'>{member.point}</td>
              <td className='border border-gray-400 px-4'>{member.date}</td>
              <td className='border border-gray-400 px-4'>
                <button
                  className='bg-red-500 text-white px-3 py-1 rounded'
                  onClick={() => handleDelete(member.id)}
                >
                  삭제
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default MemberList;
