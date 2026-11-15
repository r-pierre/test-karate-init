function fn() {
  return {
    baseUrl: karate.properties['baseUrl'] || 'http://localhost:8080',
    wiremockUrl: karate.properties['wiremockUrl'] || 'http://localhost:8089'
  };
}
