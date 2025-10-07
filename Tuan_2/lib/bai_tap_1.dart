import 'package:flutter/material.dart';

class BaiTap1 extends StatefulWidget {
  const BaiTap1({super.key});

  @override
  State<BaiTap1> createState() => _BaiTap1State();
}

class _BaiTap1State extends State<BaiTap1> {
  final TextEditingController _controller = TextEditingController();
  List<int> _numbers = [];
  String _errorMessage = '';

  void _createList() {
    setState(() {
      _errorMessage = '';
      _numbers.clear();
      
      String input = _controller.text.trim();
      
      if (input.isEmpty) {
        _errorMessage = 'Dữ liệu bạn nhập không hợp lệ';
        return;
      }
      
      // Kiểm tra xem input có phải là số không
      int? number = int.tryParse(input);
      if (number == null) {
        _errorMessage = 'Dữ liệu bạn nhập không hợp lệ';
        return;
      }
      
      // Tạo danh sách từ 1 đến số nhập vào
      for (int i = 1; i <= number; i++) {
        _numbers.add(i);
      }
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Bài tập 1 - Nhập số và tạo danh sách'),
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
              keyboardType: TextInputType.number,
              decoration: const InputDecoration(
                labelText: 'Nhập số lượng phần tử',
                border: OutlineInputBorder(),
                hintText: 'Ví dụ: 5',
              ),
            ),
            const SizedBox(height: 16),
            ElevatedButton(
              onPressed: _createList,
              style: ElevatedButton.styleFrom(
                backgroundColor: Colors.blue,
                foregroundColor: Colors.white,
                padding: const EdgeInsets.symmetric(vertical: 12),
              ),
              child: const Text('Tạo'),
            ),
            const SizedBox(height: 16),
            if (_errorMessage.isNotEmpty)
              Text(
                _errorMessage,
                style: const TextStyle(
                  color: Colors.red,
                  fontSize: 16,
                ),
                textAlign: TextAlign.center,
              ),
            const SizedBox(height: 16),
            Expanded(
              child: _numbers.isEmpty
                  ? const Center(
                      child: Text(
                        'Danh sách sẽ hiển thị ở đây',
                        style: TextStyle(
                          fontSize: 16,
                          color: Colors.grey,
                        ),
                      ),
                    )
                  : ListView.builder(
                      itemCount: _numbers.length,
                      itemBuilder: (context, index) {
                        return Container(
                          margin: const EdgeInsets.symmetric(vertical: 4),
                          padding: const EdgeInsets.all(16),
                          decoration: BoxDecoration(
                            color: Colors.red,
                            borderRadius: BorderRadius.circular(8),
                          ),
                          child: Text(
                            _numbers[index].toString(),
                            style: const TextStyle(
                              color: Colors.white,
                              fontSize: 18,
                              fontWeight: FontWeight.bold,
                            ),
                            textAlign: TextAlign.center,
                          ),
                        );
                      },
                    ),
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
