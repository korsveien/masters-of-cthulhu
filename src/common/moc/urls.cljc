(ns moc.urls)

(def urls ["/" {"" :url/index
                "auth" {"/login" :url.auth/login
                        "/register" :url.auth/register
                        ["/token/" :token] :url.auth/token
                        "/logout" :url.auth/logout}
                "dashboard" {"/account" :url.user/profile
                             "/password" :url.user/password}
                "game" {"/new" :url.game/new}
                "api" {"/auth" {"/login" :api.auth/login
                                "/register" :api.auth/register}
                       "/user" {"/me" :api.user/me}}}])
