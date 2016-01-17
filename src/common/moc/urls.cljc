(ns moc.urls)

(def urls ["/" {"" :url.dashboard/index
                "auth" {"/login" :url.auth/login
                        "/register" :url.auth/register
                        ["/token/" :token] :url.auth/token}
                "dashboard" {"/profile" :url.dashboard/profile}
                "game" {"/new" :url.dashboard/new-game}

                "api" {"/auth" {"/login" :api.auth/login
                                "/register" :api.auth/register
                                "/logout" :api.auth/logout}
                       "/user" {"/me" :api.user/me}}}])
