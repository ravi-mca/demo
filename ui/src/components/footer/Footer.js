import React from 'react';
import Moment from 'react-moment';

export default class Footer extends React.Component {

    render() {
        
        return (
            <footer class="container-fluid fixed-bottom">
			  <div class="col-lg-6"><p>© <Moment format="YYYY"/> Aussio, Inc</p></div>
			  <div class="col-lg-6">
			  	<p class="pull-right">
			  		<a href="www.storecast-io.com/terms-of-services" target="_blank">Terms of Service</a>
			  	</p>
			  </div>
			</footer>
        );
    }
}
