import grpc from 'k6/net/grpc';
import { check, sleep } from 'k6';


const client = new grpc.Client();
client.load(['protos'], 'stocks.proto');

export let options = {
  stages: [
    { duration: '10s', target: 200, rps: 1 },
    { duration: '10s', target: 400, rps: 1 },
    { duration: '10s', target: 1000, rps: 1 },
    { duration: '1m', target: 1000, rps: 1 },
  ],
};



export default () => {
  client.connect('localhost:9090', {
    plaintext: true
  });

  const data = {
    symbol: 'TTT',
    date: new Date().getTime(),
    open: 10.0,
    close: 10.0,
    high: 10.0,
    low: 10.0,
    volume: 1000
  };
  const response = client.invoke('services.StockService/Insert', data);

  check(response, {
    'status is OK': (r) => r && r.status === grpc.StatusOK,
  });

  // console.log(JSON.stringify(response.message));

  client.close();
  sleep(1);
};