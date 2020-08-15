import http from 'k6/http';
import {check, group, sleep, fail} from 'k6';

export let options = {
  vus: 1,
  iterations: 1,
};

const RESOURCE = 'http://localhost:8080/test';
const SSO = __ENV.SSO;
const CODE = __ENV.CODE;

let ACCESS_TOKEN = __ENV.ACCESS_TOKEN;

let HEADERS = {
  'Content-Type': 'application/json',
  'Authorization': 'Bearer ' + ACCESS_TOKEN,
};

// k6 run -e SSO=http://localhost:8081 -e CODE=nrbykQ tests-open.js

export default () => {

  if (!SSO) {
    console.error('You need define the URL of the SSO');
  } /*else if (!CODE) {
    console.error('First get the code to authorization code request');
    console.error('Open your browser and go to the url http://localhost:8081/oauth/authorize?response_type=code&client_id=browser&redirect_uri=http://localhost:8080/test');
    console.error('Execute the login and get the code in URL redirected (Example: http://localhost:8080/test?code=tCQlSA => tCQlSA).');
  }*/ else {

    // First get the code to authorization code request
    // Open your browser and go to the url http://localhost:8081/oauth/authorize?response_type=code&client_id=browser&redirect_uri=http://localhost:8080/test
    // Execute the login and get the code in URL redirected (Example: http://localhost:8080/test?code=tCQlSA => tCQlSA).
    group('Authorization Code', function () {

      if (CODE) {
        // Get access token from code
        let query1 = http.post(`${SSO}/oauth/token?grant_type=authorization_code&code=${CODE}&client_id=browser&client_secret=browser&redirect_uri=${RESOURCE}`);
        check(query1, {
          'Request the access token. Must be return 200': (r) => r.status === 200
        });
        check(query1.json(),
          {
            'Request the access token, must be return the Access Token and Refresh Token':
              (obj) => {

                if (!ACCESS_TOKEN)
                  ACCESS_TOKEN = obj['access_token'];
                HEADERS = {
                  'Content-Type': 'application/json',
                  'Authorization': 'Bearer ' + ACCESS_TOKEN
                }

                return obj && obj['access_token'] && obj['refresh_token'] && obj['token_type'] && obj['token_type'] === 'bearer'
              },
          }
        );
      }

      const rootAccessGroupMustBeAccess = http.get(`${RESOURCE}/api/testing`, {headers: HEADERS});
      check(rootAccessGroupMustBeAccess, {
        'Testing access for root access group - Must be access and return 200': (r) => r.status === 200,
        'Must be return \'accessed\'': (r) => r.body === 'accessed'
      });

      const mustNotBeHaveAccess = http.get(`${RESOURCE}/api/testing/not-access`, {headers: HEADERS});
      check(mustNotBeHaveAccess, {
        'Testing access for root access group - Must not be have access and return 403': (r) => r.status === 403,
        'Must be return \'not-accessed\'': (r) => r.json()['error'] === 'access_denied' && r.json()['error_description'] === 'Acesso negado'
      });

      const mustBeHavePublicAccess = http.get(`${RESOURCE}/api/testing/public-access`, {headers: HEADERS});
      check(mustBeHavePublicAccess, {
        'Testing access for anonymous access group (Public Access) - Must be have access and return 200': (r) => r.status === 200,
        'Must be return \'public-accessed\'': (r) => r.body === 'public-accessed'
      });
      // check(mustNotHaveAccess.json(), {
      //   'Testing access for root access group - Must not be have access and return 403': (r) => r.status === 403,
      //   'Must be return \'not-accessed\'': (obj) => obj.json() === {
      //     "error": "access_denied",
      //     "error_description": "Acesso negado"
      //   }
      // });
      // check(query2,
      //   console.log(query2),
      //   {
      //
      //     'Must be return accessed': (obj) => obj === 'accessed',
      //   }
      // );
      //     // Não deve ser possível apagar um feriado nacional
      //     let delr = http.del(`${BASE_URL}/feriados/4305439/05-01/`);
      //     check(delr, {
      //         'tentar remover um feriado nacional no município retorna status 403':
      //             (r) => r.status === 403,
      //     });
    });


  }


}
