export type MenuTitle = {
  menuName: string;
  tapIndex: number;
  type: string;
};

export type ApiResponse<T> = {
  code: number;
  status: string;
  message: string;
  data: T;
};

export type OrderResponse = {
  id: number;
  totalPrice: number;
  registeredDateTime: Date;
  products: ProductResponse[];
  point: number;
};

export type ProductResponse = {
  id: number;
  productNameKr: string;
  productNameEn: string;
  orderPrice: number;
  quantity: number;
};

export type Session = {
  locale: string;
  cart: Cart;
};

// export type Cart = {
//   products: Product[];
//   totalPrice: number;
// };

export type Product = {
  id: number;
  koreanName: string;
  englishName: string;
  price: number;
  quantity: number;
  optionName: string;
};

export type ProductOption = {
  id: number;
  optionName: string;
  addPrice: number;
};
