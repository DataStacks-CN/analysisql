# AnalysisQL

## getTopics（获取主题）

**request**

```json
{
  "type": "getTopics"
}
```

**response**

```json
{
  "sessionId": "...",
  "code": ...,
  "msg": "...",
  "rows": [{"value": "topic1"}, {"value": "topic2"}, ...]
}
```

## getDimentions（获取维度）

**request**

```json
{
  "type": "getDimentions",
  "topic": "topic1"
}
```

**response**

```json
{
  "sessionId": "...",
  "code": ...,
  "msg": "...",
  "rows": [{"value": "dimention1"}, {"value": "dimention2"}, ...]
}
```

## getDimentionValues（获取维度值）

**request**

```json
{
  "type": "getDimentionValues",
  "topic": "topic1",
  "dimention": "dimention1"
}
```

**response**

```json
{
  "sessionId": "...",
  "code": ...,
  "msg": "...",
  "rows": [{"value": "value1"}, {"value": "value2"}, ...]
}
```

## getMetrics（获取指标）

**request**

```json
{
  "type": "getMetrics",
  "topic": "topic1"
}
```

**response**

```json
{
  "sessionId": "...",
  "code": ...,
  "msg": "...",
  "rows": [{"value": "metric1"}, {"value": "metric2"}, ...]
}
```
