# MoneyCounter

## 概要
購入したい商品と金額を登録することで合計金額を計算してくれるアプリ

## 画面
<img src="https://github.com/tHirai67/MoneyCounter/assets/107760651/f281d12f-967c-4620-9e44-6224b54866f2" width="50%"><img src="https://github.com/tHirai67/MoneyCounter/assets/107760651/30c19a0c-38e5-41c3-9a70-16a209395489" width="50%">

## 機能
- 商品名と価格を入力するとリストに追加していき，合計金額を計算する．
- 所持金が入力されていれば，残高を計算し提示する．
- 削除ボタンを押すことによって，リストを編集することができる．
- 残高がプラスなら緑背景，マイナスなら赤背景，０なら黄色背景．
- 未入力やマイナスの値は入力を受け付けない
- ウィンドウサイズに応じて配置を変える（レスポンスデザイン）

## システム概要図&データフロー
![システム概要図](https://github.com/tHirai67/MoneyCounter/assets/107760651/28783ef1-0b26-42ef-972d-fde68c5ba00c)
フロントエンド：HTML/CSS&Thymeleaf，バックエンド：Java(SpringBoot)，IDE：Eclipse
