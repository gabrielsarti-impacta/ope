import React from 'react';
import { Link } from 'react-router-dom'

import './style.css';

const Footer = () => {
    return (
        <div className="footer">
            <Link to="/">Ir para a página inicial \o/</Link>
        </div>
    )
}

export default Footer;