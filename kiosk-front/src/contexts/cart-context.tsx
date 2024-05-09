/* eslint-disable react-refresh/only-export-components */
import {
  PropsWithChildren,
  createContext,
  useContext,
  useMemo,
  useState,
} from 'react';
import { Product } from '../types/types';

type CartContextProps = {
  cart: Product[];
  addProduct: (product: Product) => void;
  removeProduct: (id: number, optionName: string) => void;
  addOneQuantity: (id: number, optionName: string) => void;
  deductOneQuantity: (id: number, optionName: string) => void;
  resetCart: () => void;
  totalPrice: number;
  totalQuantity: number;
};

const CartContext = createContext<CartContextProps>({
  cart: [],
  addProduct: () => {},
  removeProduct: () => {},
  addOneQuantity: () => {},
  deductOneQuantity: () => {},
  resetCart: () => {},
  totalPrice: 0,
  totalQuantity: 0,
});

export const CartProvider = ({ children }: PropsWithChildren) => {
  const [cart, setCart] = useState<Product[]>([]);

  const totalPrice = useMemo(
    () =>
      cart.reduce((sum, product) => sum + product.price * product.quantity, 0),
    [cart]
  );
  const totalQuantity = useMemo(
    () => cart.reduce((sum, product) => sum + product.quantity, 0),
    [cart]
  );

  const addProduct = (product: Product) => {
    const foundProduct = cart.find(
      (p) => p.id === product.id && p.optionName === product.optionName
    );
    if (foundProduct) {
      foundProduct.quantity += product.quantity;
    } else {
      cart.push(product);
    }
    setCart([...cart]);
  };
  const removeProduct = (id: number, optionName: string) => {
    console.log(id, optionName, '>>>>>>>>>>>>>>>>>>');
    setCart([
      ...cart.filter((p) => p.id !== id || p.optionName !== optionName),
    ]);
  };
  const addOneQuantity = (id: number, optionName: string) => {
    const foundProduct = cart.find(
      (p) => p.id === id && p.optionName === optionName
    );
    if (foundProduct) {
      foundProduct.quantity += 1;
    }
    setCart([...cart]);
  };
  const deductOneQuantity = (id: number, optionName: string) => {
    const foundProduct = cart.find(
      (p) => p.id === id && p.optionName === optionName
    );
    if (foundProduct) {
      foundProduct.quantity = Math.max(1, foundProduct.quantity - 1);
    }
    setCart([...cart]);
  };
  const resetCart = () => {
    setCart([]);
  };

  return (
    <CartContext.Provider
      value={{
        cart,
        addProduct,
        removeProduct,
        addOneQuantity,
        deductOneQuantity,
        resetCart,
        totalPrice,
        totalQuantity,
      }}
    >
      {children}
    </CartContext.Provider>
  );
};
export const useCartManager = () => useContext(CartContext);
