# LineCopyPaste (Eclipse Plugin)

This is an Eclipse plugin that provides "smarter versions" of Copy/Cut/Paste actions.
When nothing is selected, the Copy and Cut actions will work on the current line. Paste
will insert lines at the start of the current line rather than at the current cursor location.
When anything is selected, the default Copy/Cut/Paste behaviour will function without any
modifications.

## Installation

Copy linecopypaste.jar to the plugins directory under the Eclipse installation and restart Eclipse.
The LineCopyPaste plugin overrides Ctrl-X/C/V by default, but the bindings can be changed

## Copyright

Copyright (c) 2011 Lars Christensen

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