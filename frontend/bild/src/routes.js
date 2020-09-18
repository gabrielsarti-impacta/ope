import React from 'react';
import { Route, Switch, BrowserRouter } from 'react-router-dom';

import Header from './components/Header';
import Footer from './components/Footer';

import Home from './pages/home';

const Routes = () => {
    return (
        <BrowserRouter>
            <Header />
            <Switch>
                <Route exact path='/' component={Home} />
            </Switch>
            <Footer />
        </BrowserRouter>
    )
}

export default Routes;
