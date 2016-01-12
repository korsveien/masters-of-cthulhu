(ns moc.urls)

(def urls ["/" {"" :url/index
                "auth" {"/login" :url.user/login
                        "/register" :url.user/register
                        ["/token/" :token] :url.user/token
                        "/logout" :url.user/logout}
                "dashboard" {"/account" :url.user/profile
                             "/password" :url.user/password}
                "game" {"/new" :url.game/new}
                "api" {"/auth" {"/login" :api.user/login
                                "/register" :api.user/register}
                       "/user" {"/me" :api.user/me}}}])
