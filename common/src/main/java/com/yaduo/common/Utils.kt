package com.yaduo.common

import android.util.Log
import java.math.BigInteger
import java.security.MessageDigest

/**
 * @author YaDuo
 * time 2025-05-14 16:21:22
 */
object Utils {

    /**
     * 根据uuid生成uid
     * 通过SHA-256哈希计算
     * 对这个整数取绝对值后模'Yaduo'的ASCII码
     */
    fun genUid(uuid: String): String {
        Log.i("Yaduo Player", "genUid: uuid = $uuid")
        val hashSHA = MessageDigest.getInstance("SHA-256").run {
            update(uuid.toByteArray(Charsets.UTF_8))
            digest()
        }

        val hashNumber = BigInteger(1, hashSHA)

        val modByYaduo = "Yaduo".let { str ->
            val asciiCodes = str.map { it.code.toString().padStart(3, '0') }
            BigInteger(asciiCodes.joinToString(""))
        }

        val uidNumber = hashNumber.mod(modByYaduo).toString().padStart(13, '0')
        Log.i("Yaduo Player", "genUid: Uid = $uidNumber")
        return uidNumber
    }
}