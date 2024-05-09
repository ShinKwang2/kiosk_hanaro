function AdminMenu({
  value,
  index,
  selectedMenu,
  setSelectedMenu,
}: {
  value: string;
  index: number;
  selectedMenu: number;
  setSelectedMenu: (index: number) => void;
}) {
  const isSelected = index === selectedMenu;

  function handleClick() {
    setSelectedMenu(index);
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
