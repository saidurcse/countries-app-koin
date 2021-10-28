# Movie-db-kotlin
<snippet>
  <content><![CDATA[
# ${1:Project Name}

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT) [![Generic badge](https://img.shields.io/badge/Github-Saidur-<COLOR>.svg)](https://shields.io/)


A simple project for The Movie DB based on Kotlin MVVM with Android Jetpack Navigation clean architecture.

![alt text](https://github.com/saidurcse/movie-db-kotlin/blob/main/app/src/main/assets/home.PNG)

## Build Process

Add your Movie db api url and image url with API key in local.properties file.

api_url=https://api.themoviedb.org/

image_url=https://image.tmdb.org/t/p/w780

api_key=YOUR_API_KEY

## Development process

Based on Test-driven development.

    1) API Service -> API Service Unit Test with api response mock files
    2) ViewModel -> ViewModel Unit Test
    3) Implmentating UI & Layouts

## Specs & Open-source libraries

    * Minimum SDK 21
    * 100% Kotlin based
    * MVVM Architecture
    * Architecture Components (Lifecycle, LiveData, ViewModel, Room Persistence)
    * DataBinding
    * Material Design & Animations
    * The Movie DB API
    * Retrofit2 & Gson for constructing the REST API
    * OkHttp3 for implementing interceptor, logging and mocking web server
    * Glide for loading images
    * BaseRecyclerViewAdapter for implementing adapters and viewHolders
    * Koin and Dagger version
    * Mockito-kotlin for Junit mock test

<!--## Contributor

![alt text](https://avatars.githubusercontent.com/u/6915467?s=160&v=4)-->

## Contributing

1. Fork it!
2. Create your feature branch: `git checkout -b my-new-feature`
3. Commit your changes: `git commit -am 'Add some feature'`
4. Push to the branch: `git push origin my-new-feature`
5. Submit a pull request :D

## License

```
The MIT License (MIT)

Copyright (c) 2021 Saidur

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
```

</content>
</snippet>
