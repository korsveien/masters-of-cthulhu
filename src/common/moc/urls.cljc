(ns moc.urls)

(def urls ["" {"" :url/index
               "/auth" {"/login" :url.user/login
                        "/register" :url.user/register}}])
