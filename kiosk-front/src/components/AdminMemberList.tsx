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
  const [selectedMember, setSelectedMember] = useState<number | null>(null);
  const [isModalOpen, setIsModalOpen] = useState<boolean>(false);

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
    setSelectedMember(id);
    setIsModalOpen(true);
  }

  const handleConfirmDelete = () => {
    if (selectedMember !== null) {
      const updatedMembers = members.filter(
        (member) => member.id !== selectedMember
      );
      setMembers(updatedMembers);
      setIsModalOpen(false);
      setSelectedMember(null);
    }
  };

  const handleCancelDelete = () => {
    setIsModalOpen(false);
    setSelectedMember(null);
  };

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
      {isModalOpen && (
        <div className='fixed top-0 left-0 w-full h-full flex justify-center items-center bg-gray-500 bg-opacity-50'>
          <div className='bg-white p-5 rounded-lg'>
            <p>정말로 삭제하시겠습니까?</p>
            <div className='flex justify-end mt-3'>
              <button
                className='bg-red-500 text-white px-3 py-1 mr-3 rounded'
                onClick={handleConfirmDelete}
              >
                예
              </button>
              <button
                className='bg-gray-500 text-white px-3 py-1 rounded'
                onClick={handleCancelDelete}
              >
                아니오
              </button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
}

export default MemberList;
