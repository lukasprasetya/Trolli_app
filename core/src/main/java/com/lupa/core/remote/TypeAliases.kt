package com.lupa.core.remote

import io.reactivex.Observable
import retrofit2.Response


typealias TroliResponse <T> = Observable<Response<BaseResponse<T>>>