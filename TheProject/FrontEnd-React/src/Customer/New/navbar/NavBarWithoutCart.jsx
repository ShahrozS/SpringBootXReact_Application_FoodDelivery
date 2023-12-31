import { Fragment, useEffect, useRef, useState } from 'react'
import { Disclosure, Menu, Transition } from '@headlessui/react'
import { Bars3Icon, BellIcon, ShoppingCartIcon, XMarkIcon } from '@heroicons/react/24/outline'
import { Link } from 'react-router-dom'

import Cart from './Cart'

// const orderid = localStorage.getItem('orderid');





const navigation = [
  { name: 'Home', Link:`/user/home`, href: '#', current: true },
  { name: 'Order  Food',Link:`/user/home/FoodItems`, href: '#', current: false },
  { name: 'Previous Orders', Link:'/user/home/previousorder',href: '#', current: false },
  { name: 'Manage Account',Link:'/user/home/manageaccount', href: '#', current: false },
  { name: `Order Status `,Link:'/user/home/orderstatus', href: '#', current: false },
  {name:'Write a Review' , Link:'/user/home/review',href:'#',current:false}
  ,{name:'Previous Reviews',link:'/user/home/previousreviews',href:'#',current:false},
]


function classNames(...classes) {
  return classes.filter(Boolean).join(' ')
}

export default function NavbarUserWithoutCart({}) {
const orderid = localStorage.getItem('orderid');

  const closeCart = () => {
    setOpenCart(false);
  };

const [openCart, setOpenCart] = useState(false);


const openCartDialog = () => {
  setOpenCart(true);
};

const closeMyJSX = () => {
  setOpenCart(false);
};


  console.log(orderid + " In the nav bar.");

  return (
    
 
 <Disclosure as="nav" className=" bg-dark">
      {({ open }) => (
        <>
          <div className=" mx-auto max-w-7xl px-2 sm:px-6 lg:px-8 w-full">
            <div className="relative flex h-16 items-center justify-between">
              <div className="absolute inset-y-0 left-0 flex items-center sm:hidden">
                {/* Mobile menu button*/}
                <Disclosure.Button className="relative inline-flex items-center justify-center rounded-md p-2 text-gray-400 hover:bg-gray-700 hover:text-white focus:outline-none focus:ring-2 focus:ring-inset focus:ring-white">
                  <span className="absolute -inset-0.5" />
                  <span className="sr-only">Open main menu</span>
                  {open ? (
                    <XMarkIcon className="block h-6 w-6" aria-hidden="true" />
                  ) : (
                    <Bars3Icon className="block h-6 w-6" aria-hidden="true" />
                  )}
                </Disclosure.Button>
              </div>
              <div className="flex flex-1 items-center justify-center sm:items-stretch sm:justify-start">
               
                <div className="hidden sm:ml-6 sm:block">
                  <div className="flex space-x-4">
                    {navigation.map((item) => (
                    <Link to={item.Link}> <a
                        key={item.name}
                        href={item.href}
                      
                        className={classNames(
                          item.current ? 'bg-gray-900 text-white' : 'text-gray-300 hover:bg-gray-700 hover:text-white',
                          'rounded-md px-3 py-2 text-sm font-medium'
                        )}
                        aria-current={item.current ? 'page' : undefined}
                      > 
                        {item.name}
                      </a></Link> 
                    ))}
                  </div>
                </div>
              </div>
              <Link to="/auth/Login">
                <button href="SignUp.js" className=" bg-red text-white font-semibold px-2 text-lg py-1 rounded-lg ml-2">Log out</button>
                </Link>
            </div>
            
          </div>
        
          <Disclosure.Panel className="sm:hidden">
            <div className="space-y-1 px-2 pb-3 pt-2">
              {navigation.map((item) => (
             
                <Disclosure.Button 
                  key={item.name}
                  as="a"
                  href={item.href}
                 
                  className={classNames(
                    item.current ? 'bg-gray-900 text-white' : 'text-gray-300 hover:bg-gray-700 hover:text-white',
                    'block rounded-md px-3 py-2 text-base font-medium'
                  )}
                  aria-current={item.current ? 'page' : undefined}
                >
                  {item.name}
                </Disclosure.Button>  
              ))}
            </div>
          </Disclosure.Panel>
        </>
      )}
      
    </Disclosure>
    
  )
}
