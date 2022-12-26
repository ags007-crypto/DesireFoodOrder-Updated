package com.firstapp.foodorder.model

class customerorder {
    var name: String? =null
    var phone: String? = null
    var type: String? = null

    constructor(name: String?,phone: String?, type: String?) {
        this.name=name
        this.phone = phone
        this.type = type
    }

    constructor() {}
}