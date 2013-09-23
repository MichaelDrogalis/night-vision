# night-vision

Peer through the darkness of running code.

## Usage

In your Leiningen profile:

```clojure
{:user                                                                                                                                                                                                              
 {:dependencies [[night-vision "0.1.0-SNAPSHOT"]]
  :injections [(require 'night-vision.goggles)
               (require 'clojure.pprint)]}}
```

Enable Night Vision Goggles on a quoted namespace:
`(night-vision.goggles/introspect-ns! 'user)`

## Example

```clojure
user=> (defn odd?? [n] (odd? n))
#'user/odd??
user=> (defn even?? [n] (not (odd?? n)))
#'user/even??
user=> (odd?? 1)
true
user=> (odd?? 2)
false
user=> (night-vision.goggles/introspect-ns! 'user)
nil
user=> (odd?? 1)
"------------------------------------------------"
"Invoking #'user/odd??"
"Actual arguments are: (1)"
"------------------------------------------------"
"------------------------------------------------"
"Returning from #'user/odd??"
"Return value is: true"
"------------------------------------------------"
true
user=> (odd?? 2)
"------------------------------------------------"
"Invoking #'user/odd??"
"Actual arguments are: (2)"
"------------------------------------------------"
"------------------------------------------------"
"Returning from #'user/odd??"
"Return value is: false"
"------------------------------------------------"
false
user=> (even?? 2)
"------------------------------------------------"
"Invoking #'user/even??"
"Actual arguments are: (2)"
"------------------------------------------------"
"------------------------------------------------"
"Invoking #'user/odd??"
"Actual arguments are: (2)"
"------------------------------------------------"
"------------------------------------------------"
"Returning from #'user/odd??"
"Return value is: false"
"------------------------------------------------"
"------------------------------------------------"
"Returning from #'user/even??"
"Return value is: true"
"------------------------------------------------"
true
```

## License

Copyright © 2013 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
