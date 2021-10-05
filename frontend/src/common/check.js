import jwt_decode from 'jwt-decode';

export default() => {
    let user = jwt_decode(localStorage.getItem('accessToken'));
    if (user.exp < Date.now() / 1000) {
      /*만료되었으면 */
      user = jwt_decode(localStorage.getItem('refreshToken'));
      if (user.exp < Date.now() / 1000) {
        location.href = '/user/login';
      } else {
        return 1; /* refresh 토큰 만료 안됨! */
      }
    }
    return 0; /* access 토큰 만료 안됨! */

}