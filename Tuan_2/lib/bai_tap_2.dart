import 'package:flutter/material.dart';

class BaiTap2 extends StatefulWidget {
  const BaiTap2({super.key});

  @override
  State<BaiTap2> createState() => _BaiTap2State();
}

class _BaiTap2State extends State<BaiTap2> {
  final TextEditingController _controller = TextEditingController();
  String _message = '';
  Color _messageColor = Colors.black;

  void _checkEmail() {
    setState(() {
      String email = _controller.text.trim();
      
      if (email.isEmpty) {
        _message = 'Email không hợp lệ';
        _messageColor = Colors.red;
      } else if (!email.contains('@')) {
        _message = 'Email không đúng định dạng';
        _messageColor = Colors.red;
      } else {
        _message = 'Bạn đã nhập email hợp lệ';
        _messageColor = Colors.green;
      }
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Bài tập 2 - Kiểm tra email'),
        backgroundColor: Colors.blue,
        foregroundColor: Colors.white,
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.stretch,
          children: [
            TextField(
              controller: _controller,
              keyboardType: TextInputType.emailAddress,
              decoration: const InputDecoration(
                labelText: 'Nhập email',
                border: OutlineInputBorder(),
                hintText: 'Ví dụ: example@gmail.com',
              ),
            ),
            const SizedBox(height: 16),
            ElevatedButton(
              onPressed: _checkEmail,
              style: ElevatedButton.styleFrom(
                backgroundColor: Colors.blue,
                foregroundColor: Colors.white,
                padding: const EdgeInsets.symmetric(vertical: 12),
              ),
              child: const Text('Kiểm tra'),
            ),
            const SizedBox(height: 16),
            if (_message.isNotEmpty)
              Container(
                padding: const EdgeInsets.all(12),
                decoration: BoxDecoration(
                  color: _messageColor == Colors.red 
                      ? Colors.red.withOpacity(0.1)
                      : Colors.green.withOpacity(0.1),
                  borderRadius: BorderRadius.circular(8),
                  border: Border.all(
                    color: _messageColor,
                    width: 1,
                  ),
                ),
                child: Text(
                  _message,
                  style: TextStyle(
                    color: _messageColor,
                    fontSize: 16,
                    fontWeight: FontWeight.w500,
                  ),
                  textAlign: TextAlign.center,
                ),
              ),
            const Spacer(),
            const Text(
              'Hướng dẫn:\n'
              '• Email rỗng → "Email không hợp lệ"\n'
              '• Không có ký tự @ → "Email không đúng định dạng"\n'
              '• Email hợp lệ → "Bạn đã nhập email hợp lệ"',
              style: TextStyle(
                fontSize: 14,
                color: Colors.grey,
                fontStyle: FontStyle.italic,
              ),
              textAlign: TextAlign.center,
            ),
          ],
        ),
      ),
    );
  }

  @override
  void dispose() {
    _controller.dispose();
    super.dispose();
  }
}
