import http from 'k6/http';
import { check, sleep } from 'k6';

export let options = {
  stages: [
    { duration: '10s', target: 10, rps: 1 },
    { duration: '10s', target: 10, rps: 1 },
    { duration: '10s', target: 20, rps: 1 },
    { duration: '1h', target: 20, rps: 1 },
  ],
};

export default function () {

  const payload = JSON.stringify({
    symbol: 'ZZZ',
    date: new Date().toISOString(),
    open: 10.0,
    close: 10.0,
    high: 10.0,
    low: 10.0,
    volume: 1000
  });

  var params = {
    headers: {
      'Content-Type': 'application/json',
    },
  };

  let res = http.post("http://localhost:8080/stock-quotes", payload, params);
  // console.log(JSON.stringify(res))
  check(res, { 'status was 200': (r) => r.status == 200 });
  sleep(1);
}