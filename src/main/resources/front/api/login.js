function loginApi(data) {
    return $axios({
      'url': '/user/login',
      'method': 'post',
      data
    })
  }

function loginoutApi() {
  return $axios({
    'url': '/user/loginout',
    'method': 'post',
  })
}

function sendMsgApi(phone){
    return $axios({
        'url' : '/user/sendMsg',
        'method' : 'post',


        data : JSON.stringify(phone),
        headers: {
            'contentType': "application/json;charset=utf-8",
        }
    }).then(resp =>{

    })
}

  