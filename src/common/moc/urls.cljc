(ns moc.urls)

(def urls ["" {"" :url/index
               "/auth" {"/login" :url.user/login
                        "/register" :url.user/register
                        ["/token/" :token] :url.user/token}
               "/api" {"/auth" {"/login" :api.user/login
                                "/register" :api.user/register}}}])
