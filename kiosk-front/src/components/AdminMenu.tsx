import { useState } from 'react';

function AdminMenu({ value, onClick }: { value: string; onClick: () => void }) {
  const [isSelected, setIsSelected] = useState<boolean>(false);

  function handleClick() {
    setIsSelected((prev) => !prev);
    onClick();
  }

  return (
    <button
      className={`rounded-lg border-2 p-2 w-40 m-2 shadow-lg ${
        isSelected ? 'bg-green-900 text-white' : ''
      }`}
      onClick={handleClick}
    >
      {value}
    </button>
  );
}

export default AdminMenu;
