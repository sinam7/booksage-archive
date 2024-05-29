import React from "react";
import {Card, CardContent} from "@/components/ui/card";
import {CheckIcon, XIcon} from "@/components/ui/icon";


interface BookCardProps {
    title: string;
    author: string;
    publisher: string;
    price: string;
    imageSrc: string;
    href: string;
    isAvailable: boolean;

}

export function BookCard({title, author, publisher, price, imageSrc, href, isAvailable}: BookCardProps) {
    // @ts-ignore
    // @ts-ignore
    return (<Card>
        <CardContent className="flex items-center justify-between mt-4">
            <div className="flex items-center gap-8">
                <img
                    alt="Feature 1"
                    className="rounded-md"
                    height={48}
                    src={imageSrc}
                    style={{
                        aspectRatio: "48/48",
                        objectFit: "cover",
                    }}
                    width={48}
                />
                <div className="space-y-1">
                    <a href={href}><h4 className="font-medium">{title}</h4></a>
                    <div className="text-sm text-gray-500 dark:text-gray-400">
                        <span>{author}</span>
                        <span className="mx-2">•</span>
                        <span>{publisher}</span>
                        <br/>
                        <span>{price}</span>
                    </div>
                </div>
            </div>
            {isAvailable ?
                <CheckIcon className="w-5 h-5 text-green-500"/> :
                <XIcon className="w-5 h-5 text-red-500"/>}
        </CardContent>
    </Card>);
}