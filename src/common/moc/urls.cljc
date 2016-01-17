(ns moc.urls)

(def urls ["/" {"" :url/index
                "auth" {"/login" :url.auth/login
                        "/register" :url.auth/register
                        ["/token/" :token] :url.auth/token}
                "dashboard" {"/account" :url.user/profile
                             "/password" :url.user/password}
                "game" {"/new" :url.game/new}
                "api" {"/auth" {"/login" :api.auth/login
                                "/register" :api.auth/register
                                "/logout" :api.auth/logout}
                       "/user" {"/me" :api.user/me}}}])
