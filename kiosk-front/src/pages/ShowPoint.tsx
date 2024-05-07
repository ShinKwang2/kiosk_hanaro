function ShowPoint({ point }: { point: number }) {
  return (
    <div className='m-10 bg-green-900 p-20 w-50 flex justify-center'>
      <div className='bg-white rounded-2xl text-center px-32 py-14'>
        <p className='font-bold text-lg'>
          <span className='text-red-500'>{point}P </span>
          적립되었습니다!
        </p>
        <button className='font-bold border-gray-200 border-2 hover:bg-green-700 hover:text-white mt-5 p-2 w-48 rounded-lg shadow-xl'>
          확인
        </button>
      </div>
    </div>
  );
}

export default ShowPoint;
