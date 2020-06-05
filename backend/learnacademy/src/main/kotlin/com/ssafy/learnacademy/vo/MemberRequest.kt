package com.ssafy.learnacademy.vo

import com.fasterxml.jackson.annotation.JsonProperty

class MemberRequest (
    @JsonProperty
    var email: String? = null,
    @JsonProperty
    var password: String? = null,
    @JsonProperty
    var type: String? = null
)