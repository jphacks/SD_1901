# インフラ・APIまわりについて

## 起動方法

1. `docker-compose.yml`内のAPIキー等を編集する
1. `docker-compose up`でクラスタを立ち上げる
1. `http://localhost:8492`をbaseURLとしてAPIが起動します

## 起動準備

ここでは、docker-composeを書いています。
environment内にある各変数を編集してください。

### DBまわり

- DB_URL: postgreSQLのURIを指定します
- DB_USER: postgreSQLのユーザー名を指定します
- DB_PASSWORD: postgreSQLのパスワードを指定します

postgreSQLはAWSのRDBで簡単に立ち上げられます(無料枠有)。

### S3(ファイルストレージ)まわり

- AWS_ACCESS_KEY_ID: AWSのIAMで発行したs3に関する権限のあるキーIDを入れてください
- AWS_ACCESS_ACCESS_KEY: AWSのIAMで発行したs3に関する権限のあるアクセスキーを入れてください
- S3_URL: s3バケットのURLを入力します。`https://<バケット名>.s3.amazonaws.com`といったフォーマットになります。
