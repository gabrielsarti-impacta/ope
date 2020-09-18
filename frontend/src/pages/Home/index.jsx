import React from 'react';
import { Link } from 'react-router-dom'

import './style.css';

const Home = () => {
    return (
        <div className="home">
            <Link to="/alo">Ir para a página 404 \o/</Link>
        </div>
    )
}

export default Home;