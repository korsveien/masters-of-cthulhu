(ns moc.urls)

(def urls ["" {"" :index
               "/auth" {"/login" :user/login
                        "/register" :user/register}}])
