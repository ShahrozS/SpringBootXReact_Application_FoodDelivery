//import logo from './logo.svg';
import './App.css';
//import Navigation from './Customer/components/navigation/navigation';
import HomePage from './Customer/pages/HomePage/homepage';
import SignUp from './Customer/Login_Register/SignUp';
import { BrowserRouter as Router, Route ,Routes} from 'react-router-dom';
import Categories from './Admin/Categories/Categories';






//aosdjaasdas




function App() {
  return (

  <div className=''> 
  
    <Router>
     <Routes>
        <Route path="/" element={<HomePage/>} />
        <Route path="/signup" element={<SignUp/>} />
        <Route path="/categories" element={<Categories/>}/>
       
    
           
      
      </Routes>
    </Router>


</div>
   
  );
}

export default App;
