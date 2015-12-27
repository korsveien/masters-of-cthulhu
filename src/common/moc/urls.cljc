(ns moc.urls)

(def urls ["" {"" :index
               "/api" :api
               "/auth" {"/login" :user/login
                        "/register" :user/register}}])
