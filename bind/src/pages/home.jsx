import React from 'react';

import { messages } from '../helpers';

import Text from '../components/Text';

const Home = () => {
    return (
        <div className="bd-home">
            <Text text={messages.home.text}/>
        </div>
    );
};

export default Home;
