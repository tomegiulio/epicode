import { Map, GoogleApiWrapper, Marker } from 'google-maps-react'
import { Component } from 'react'


class MapTest extends Component {
  handleClick = () => {
    const { lat, lng } = this.props
    window.open(`https://www.google.com/maps/search/?api=1&query=${this.props.latProp},${this.props.lngProp}`)
  }

  render() {
    const mapStyles = {
      maxWidth: '800px',
      maxHeight: '400px'
    }

    

    return (
      <div className=' overflow-hidden bg-gray-100 min-h-screen'>
        <Map
          
          google={this.props.google}
          style={mapStyles}
          initialCenter={{ lat: this.props.latProp, lng: this.props.lngProp }}
        >
          <Marker
            position={{ lat: this.props.latProp, lng: this.props.lngProp }}
            onClick={this.handleClick}
          />
        </Map>
      </div>
    )
  }
}
  
export default GoogleApiWrapper({
    apiKey:"AIzaSyBlxTrMPH_4P-kXOR4DqjCTj23iyFmpmgk"
})(MapTest)
//YGw5OlNaCXVSUD4tlLkg/Q8v8pPHdqXDx8PGfqVX
//GOOG1E3FFDED4MXELCANI2ZRISFCOBCRHDPFAYX3P2DYHA2DRTRSZ2ZCMWXJQ
//ice_bucket_challange