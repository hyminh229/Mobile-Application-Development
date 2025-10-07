import 'package:flutter/material.dart';

class BaiTapVeNha extends StatefulWidget {
  const BaiTapVeNha({super.key});

  @override
  State<BaiTapVeNha> createState() => _BaiTapVeNhaState();
}

class _BaiTapVeNhaState extends State<BaiTapVeNha> {
  final TextEditingController _nameController = TextEditingController();
  final TextEditingController _ageController = TextEditingController();
  String _result = '';
  String _errorMessage = '';

  void _checkAge() {
    setState(() {
      _result = '';
      _errorMessage = '';
      
      String name = _nameController.text.trim();
      String ageText = _ageController.text.trim();
      
      if (name.isEmpty) {
        _errorMessage = 'Vui lòng nhập họ và tên';
        return;
      }
      
      if (ageText.isEmpty) {
        _errorMessage = 'Vui lòng nhập tuổi';
        return;
      }
      
      int? age = int.tryParse(ageText);
      if (age == null) {
        _errorMessage = 'Tuổi phải là số hợp lệ';
        return;
      }
      
      if (age < 0) {
        _errorMessage = 'Tuổi không thể âm';
        return;
      }
      
      // Phân loại theo tuổi
      if (age > 65) {
        _result = 'Người già';
      } else if (age >= 6 && age <= 65) {
        _result = 'Người lớn';
      } else if (age >= 2 && age < 6) {
        _result = 'Trẻ em';
      } else if (age < 2) {
        _result = 'Em bé';
      }
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Bài tập về nhà - Phân loại tuổi'),
        backgroundColor: Colors.blue,
        foregroundColor: Colors.white,
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.stretch,
          children: [
            TextField(
              controller: _nameController,
              decoration: const InputDecoration(
                labelText: 'Họ và tên',
                border: OutlineInputBorder(),
                hintText: 'Ví dụ: Nguyễn Văn A',
              ),
            ),
            const SizedBox(height: 16),
            TextField(
              controller: _ageController,
              keyboardType: TextInputType.number,
              decoration: const InputDecoration(
                labelText: 'Tuổi',
                border: OutlineInputBorder(),
                hintText: 'Ví dụ: 25',
              ),
            ),
            const SizedBox(height: 16),
            ElevatedButton(
              onPressed: _checkAge,
              style: ElevatedButton.styleFrom(
                backgroundColor: Colors.blue,
                foregroundColor: Colors.white,
                padding: const EdgeInsets.symmetric(vertical: 12),
              ),
              child: const Text('Kiểm tra'),
            ),
            const SizedBox(height: 16),
            if (_errorMessage.isNotEmpty)
              Container(
                padding: const EdgeInsets.all(12),
                decoration: BoxDecoration(
                  color: Colors.red.withOpacity(0.1),
                  borderRadius: BorderRadius.circular(8),
                  border: Border.all(
                    color: Colors.red,
                    width: 1,
                  ),
                ),
                child: Text(
                  _errorMessage,
                  style: const TextStyle(
                    color: Colors.red,
                    fontSize: 16,
                    fontWeight: FontWeight.w500,
                  ),
                  textAlign: TextAlign.center,
                ),
              ),
            if (_result.isNotEmpty)
              Container(
                margin: const EdgeInsets.only(top: 16),
                padding: const EdgeInsets.all(16),
                decoration: BoxDecoration(
                  color: Colors.green.withOpacity(0.1),
                  borderRadius: BorderRadius.circular(8),
                  border: Border.all(
                    color: Colors.green,
                    width: 2,
                  ),
                ),
                child: Text(
                  _result,
                  style: const TextStyle(
                    color: Colors.green,
                    fontSize: 20,
                    fontWeight: FontWeight.bold,
                  ),
                  textAlign: TextAlign.center,
                ),
              ),
            const Spacer(),
            Container(
              padding: const EdgeInsets.all(12),
              decoration: BoxDecoration(
                color: Colors.blue.withOpacity(0.1),
                borderRadius: BorderRadius.circular(8),
                border: Border.all(
                  color: Colors.blue,
                  width: 1,
                ),
              ),
              child: const Text(
                'Phân loại tuổi:\n'
                '• Tuổi > 65: Người già\n'
                '• Tuổi 6-65: Người lớn\n'
                '• Tuổi 2-6: Trẻ em\n'
                '• Tuổi < 2: Em bé',
                style: TextStyle(
                  fontSize: 14,
                  color: Colors.blue,
                  fontWeight: FontWeight.w500,
                ),
                textAlign: TextAlign.center,
              ),
            ),
          ],
        ),
      ),
    );
  }

  @override
  void dispose() {
    _nameController.dispose();
    _ageController.dispose();
    super.dispose();
  }
}
