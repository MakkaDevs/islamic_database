<h1>Islamic App Database Extractor🕌 💾</h1>

<p>This project curates databases from published Islamic apps and offers them in separate formats (JSON, CSV) to empower developers working on related projects.✨</p>

<h2>Features</h2>

* <strong>Extracts SQLite databases</strong> from within project directories.🔍
* <strong>Converts extracted database tables</strong> into JSON format. 🔄
* Supports optional CSV format conversion.📊
* <strong>Concurrent processing</strong> for efficiency.🚀

<h2>Usage</h2>

**Prerequisites**

* Java Development Kit (JDK) version 8 or higher.☕
* A build system like Maven or Gradle (optional, but recommended)⚙️

**Steps**

1. <strong>Clone the repository:</strong>
   ```bash
   git clone https://github.com/VersaInnovates/Islamic-Apps-Datatbases.git

**Place Additional SQLite databases:**

* Place your SQLite database files inside "sqlite" directory.

**Run the main function:**

* Execute the `main()` function within the `org.parser` package.

<h2>Example</h2>

<pre><code>sqlite/
    app1.sqlite
    app2.sqlite
    subdirectory/
        app3.sqlite
</code></pre>

<p><strong>Output:</strong></p>

<pre><code>json/
    app1/
        table1.json
        table2.json 
    app2/
        ...
    subdirectory/
        ...
csv/ 
    ... 
</code></pre>

<h2>Contributing</h2>

<p>We welcome contributions! See our contribution guidelines in the CONTRIBUTING.md file. Please consider:</p>

* Adding support for more database types.
* Creating exporters for additional formats (XML, etc.).
* Implementing tests.

<h2>License</h2>

This project is licensed under [State the license type e.g.,  MIT License].  See the LICENSE file for details.