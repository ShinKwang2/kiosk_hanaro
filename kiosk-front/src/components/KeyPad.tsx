import clsx from 'clsx';

function KeyPad({
  value,
  onClick,
  phoneNumber,
}: {
  value: number;
  onClick: (value: number) => void;
  phoneNumber: string;
}) {
  function handleClick() {
    if (value === 10) {
      onClick(-1);
    } else if (value === 11) {
      onClick(0);
    } else {
      onClick(value);
    }
  }

  return (
    <button
      className={clsx(
        'border-2 border-gray-300 bg-white p-4 m-2 shadow-md rounded-md w-20 h-20 font-bold flex justify-center items-center',
        value === 10
          ? 'bg-gray-300 text-2xl'
          : value === 12
            ? 'bg-red-500 text-white text-xl'
            : 'text-2xl text-gray-400 hover:text-white hover:bg-gray-300',
        {
          [clsx('opacity-50 cursor-not-allowed')]:
            value === 12 && phoneNumber.length !== 11,
        } // phoneNumber의 길이가 11이 아닐 때만 적용
      )}
      onClick={handleClick}
    >
      {value === 10 ? '←' : value === 11 ? 0 : value === 12 ? '확인' : value}
    </button>
  );
}

export default KeyPad;
