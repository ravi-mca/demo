import React from 'react';
import Moment from 'react-moment';

export default class Footer extends React.Component {

    render() {
        
        return (
            <footer class="container-fluid fixed-bottom">
            <div class="flex-container">
			  <div class="flex-item"><p>© <Moment format="YYYY"/> Aussio, Inc</p></div>
			  <div class="flex-item right-text">
			  	<p class="">
			  		<a href="www.storecast-io.com/terms-of-services" target="_blank">Terms of Service</a>
			  	</p>
			  </div>
			</div>  
			</footer>
        );
    }
}
