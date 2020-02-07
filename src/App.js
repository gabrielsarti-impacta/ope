import React from 'react';
import { BrowserRouter, Switch, Route } from 'react-router-dom';

// pages
import Home from './pages/Home';
import NotFound from './pages/NotFound';


// components
import Header from './components/Header';
import Footer from './components/Footer';


function App() {
  return (
    <>
        <BrowserRouter>
        <Header />
            <Switch>
                <Route path="/" exact={true} component={Home} />
                <Route path="*" exact={true} component={NotFound} />
            </Switch>
        <Footer />
        </BrowserRouter>
    </>
  );
}

export default App;
