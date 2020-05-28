import request from "@/utils/request";

export const login = (data) => {
  return request({
    url: "member/signin",
    method: "post",
    data,
  });
};

export const join = (data) => {
  for (var value of data.values()) {
    console.log(value);
  }
  return request({
    url: "member/signup",
    method: "post",
    data,
  });
};

export const requestEmail = (data) => {
  return request({
    url: "verify/sendEmail",
    method: "post",
    data,
  });
};

export const emailCheck = (data) => {
  return request({
    url: "verify/checkCode",
    method: "post",
    data,
  });
};

export const findPassword = (data) => {
  return request({
    url: "member/findPassword",
    method: "post",
    data,
  });
};
