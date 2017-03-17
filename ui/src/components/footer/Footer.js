import React from 'react';

export default class Footer extends React.Component {
    displayName = 'Footer component wrapper'

    render() {
        return (
            <footer class="container-fluid fixed-bottom">
			  <div class="col-lg-6"><p>© 2017 Aussio, Inc</p></div>
			  <div class="col-lg-6">
			  	<p class="pull-right">
			  		<a href="www.storecast-io.com/terms-of-services" target="_blank">Terms of Service</a>
			  	</p>
			  </div>
			</footer>
        );
    }
}
