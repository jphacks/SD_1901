const express = require('express');
const app = express();

const port = 3000;

app.listen(port, () => {
    console.log(`testserver is listing on port ${port}`);
});

app.put('/desk', (req, res) => {
    res.json({
        "desk_id": "rBlwQRzrRRENGZJU"
    });
});

app.post('/desk/rBlwQRzrRRENGZJU', (req, res) => {
    itemsSender(res);
});

app.put('/desk/rBlwQRzrRRENGZJU', (req, res) => {
    itemsSender(res);
});

app.get('/desk/rBlwQRzrRRENGZJU', (req, res) => {
    itemsSender(res);
});

app.get('/desk/rBlwQRzrRRENGZJU/fHde0buoEVg4qK2s', (req, res) => {
    res.sendFile('resources/lec06.pdf', { "root": __dirname } );
});

app.get('/desk/rBlwQRzrRRENGZJU/jrdJryosgPalbrlB', (req, res) => {
    res.sendFile('resources/preparation06.pdf', { "root": __dirname } );
});

app.get('/desk/rBlwQRzrRRENGZJU/ODXW4qoFGJG68uT8', (req, res) => {
    res.sendFile('resources/quiz06.pdf', { "root": __dirname } );
});

app.get('/desk/rBlwQRzrRRENGZJU/tH6hkGf8Nc5ON49O', (req, res) => {
    res.sendFile('resources/ASGRA.jpg', { "root": __dirname } );
});

app.get('/desk/rBlwQRzrRRENGZJU/7u4iddZfOeElO7rJ', (req, res) => {
    res.sendFile('resources/ER-diagram.png', { "root": __dirname } );
});

app.get('/desk/rBlwQRzrRRENGZJU/2z13qkrQfALfXvfP', (req, res) => {
    res.sendFile('resources/op.mp4', { "root": __dirname } );
});

app.get('/desk/rBlwQRzrRRENGZJU/4WvT1gdUVkEBTLGm', (req, res) => {
    res.sendFile('resources/yamada.mp3', { "root": __dirname } );
});

app.get('/desk/rBlwQRzrRRENGZJU/nZCIKAddlr3TJ4pG', (req, res) => {
    res.sendFile('resources/qr.png', { "root": __dirname } );
});

app.get('/desk/rBlwQRzrRRENGZJU/GNP06fa3Ke23reee', (req, res) => {
    res.send('今回、問３の出来が激しくよくなかった。次回は中間テストなので、計算をもう一度確認しておくこと');
});

app.get('/desk/rBlwQRzrRRENGZJU/0ztDvHO0dGQpvaTF', (req, res) => {
    res.sendFile('thumbnails/lec06.png', { "root": __dirname } );
});

app.get('/desk/rBlwQRzrRRENGZJU/O5Qv0MCrtQaC11mc', (req, res) => {
    res.sendFile('thumbnails/preparation06.png', { "root": __dirname } );
});

app.get('/desk/rBlwQRzrRRENGZJU/pSWD40Niif5Jg8mI', (req, res) => {
    res.sendFile('thumbnails/quiz06.png', { "root": __dirname } );
});

app.get('/desk/rBlwQRzrRRENGZJU/3XiKdpkh8Y2nanX4', (req, res) => {
    res.sendFile('thumbnails/op.png', { "root": __dirname } );
});

function itemsSender(res) {
    res.json({
        "item_info_list": [
            {
                "item_id": "fHde0buoEVg4qK2s",
                "name": "講義資料.pdf",
                "type": "file",
                "thumbnail_id": "0ztDvHO0dGQpvaTF"
            },
            {
                "item_id": "jrdJryosgPalbrlB",
                "name": "宿題.pdf",
                "type": "file",
                "thumbnail_id": "O5Qv0MCrtQaC11mc"
            },
            {
                "item_id": "ODXW4qoFGJG68uT8",
                "name": "小テスト.pdf",
                "type": "file",
                "thumbnail_id": "pSWD40Niif5Jg8mI"
            },
            {
                "item_id": "tH6hkGf8Nc5ON49O",
                "name": "筋肉.png",
                "type": "file"
            },    
            {
                "item_id": "7u4iddZfOeElO7rJ",
                "name": "俯瞰図.png",
                "type": "file"
            },
            {
                "item_id": "2z13qkrQfALfXvfP",
                "name": "op.mp4",
                "type": "file",
                "thumbnail_id": "3XiKdpkh8Y2nanX4",
            },
            {
                "item_id": "4WvT1gdUVkEBTLGm",
                "name": "yamada.mp3",
                "type": "file"
            },
            {
                "item_id": "GNP06huCXAp1S0mh",
                "name": "半導体デバイス授業のページ",
                "type": "url",
                "qr_id": "nZCIKAddlr3TJ4pG"
            },
            {
                "item_id": "GNP06fa3Ke23reee",
                "name": "今回の授業についての補足",
                "type": "text"
            }
        ]
    });
}