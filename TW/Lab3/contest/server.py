# -*- coding: utf-8 -*-

from http.server import BaseHTTPRequestHandler, HTTPServer
import socketserver
import socket
import sys

class ConfigHTTPRequestHandler(BaseHTTPRequestHandler):
    def _set_headers(self):
        self.send_response(200)
        self.send_header('Content-type', 'text/html')
        self.end_headers()

    def do_HEAD(self):
        self._set_headers()

    def do_GET(self):
        print ("coisas")

        if self.path == '/':
            self.path = 'index.html'

        self._set_headers()

        if self.path.startswith("/"):
            self.path=self.path[1:]
            
        with open(self.path, "rb") as f:
            self.wfile.write(f.read())
            
    def do_POST(self):
        print(self.headers)

        content_length = int(self.headers.get('Content-Length', 0))
        config_string = self.rfile.read(content_length).decode("UTF-8")
        print("Content length: ", content_length)
        print("DATA: [ ", config_string, " ]")

        self._set_headers()
        self.wfile.write(b'Hello, world! (from do_POST())<br>')
        self.wfile.write(str.encode(config_string))
        return

    
ConfigHTTPRequestHandler.protocol_version = "HTTP/1.0"
httpd = HTTPServer(("127.0.0.1", 8080), ConfigHTTPRequestHandler)

sa = httpd.socket.getsockname()
print("Serving HTTP on", sa[0], "port", sa[1], "...")
try:
    httpd.serve_forever()
except KeyboardInterrupt:
    print("\nKeyboard interrupt received, exiting.")
    httpd.server_close()
    sys.exit(0)
